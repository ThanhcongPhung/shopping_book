package com.thanhcong.shop.Controller;

import com.thanhcong.shop.Repository.OrderRepository;
import com.thanhcong.shop.Service.ProductService;
import com.thanhcong.shop.form.CustomerForm;
import com.thanhcong.shop.info.CartInfo;
import com.thanhcong.shop.info.CustomerInfo;
import com.thanhcong.shop.info.ProductInfo;
import com.thanhcong.shop.model.Products;
import com.thanhcong.shop.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class MainController {
    @Autowired
    ProductService productRepository;
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("/403")
    public String accessDenied() {
        return "/403";
    }

    @RequestMapping("/")
    public String home() {

        return "index";
    }

    @RequestMapping({"/productList"})
    public String listProductHandler(Model model,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                     @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {

        Pageable pageable = PageRequest.of(page, size);
        model.addAttribute("listProducts", productRepository.findAll(pageable));
        return "productList";
    }

    @RequestMapping({"/buyProduct"})
    public String listProductHandler(HttpServletRequest request,
                                     @RequestParam(value = "code", defaultValue = "") String code) {
        Products productsEntity = null;
        if (code != null && code.length() > 0) {
            productsEntity = productRepository.findByCode(code);
        }
        if (productsEntity != null) {
            CartInfo cartInfo = Utils.getCartInSession(request);
            ProductInfo product = new ProductInfo(productsEntity);
            cartInfo.addProduct(product, 1);
        }
        return "redirect:/shoppingCart";
    }

    @RequestMapping({"/shoppingCartRemoveProduct"})
    public String removeProductHandler(HttpServletRequest request,
                                       @RequestParam(value = "code", defaultValue = "") String code) {
        Products productsEntity = null;
        if (code != null && code.length() > 0) {
            productsEntity = productRepository.findByCode(code);
        }
        if (productsEntity != null) {
            CartInfo cartInfo = Utils.getCartInSession(request);
            ProductInfo productInfo = new ProductInfo(productsEntity);
            cartInfo.removeProduct(productInfo);
        }
        return "redirect:/shoppingCart";
    }


    @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, @ModelAttribute("cartForm") CartInfo cartForm) {
        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);
        return "redirect:/shoppingCart";
    }

    @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);
        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }

    @RequestMapping(value = {"/shoppingCartCustomer"}, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
        if (cartInfo.isEmpty()) {
            return "redirect:/shoppingCart";
        }
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        CustomerForm customerForm = new CustomerForm(customerInfo);
        model.addAttribute("customerForm", customerForm);
        return "shoppingCartCustomer";
    }

    @RequestMapping(value = {"/shoppingCartCustomer"}, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request,
                                           @ModelAttribute("customerForm") @Validated CustomerForm customerForm,
                                           BindingResult result) {
        if (result.hasErrors()) {
            customerForm.setValid(false);
            return "shoppingCartCustomer";
        }
        customerForm.setValid(true);
        CartInfo cartInfo = Utils.getCartInSession(request);
        CustomerInfo customerInfo = new CustomerInfo(customerForm);
        cartInfo.setCustomerInfo(customerInfo);
        return "redirect:/shoppingCartConfirmation";
    }

    @RequestMapping(value = {"/shoppingCartConfirmation"}, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession((request));
        if (cartInfo == null || cartInfo.isEmpty()) {
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            return "redirect:/shoppingCartCustomer";
        }
        model.addAttribute("myCart", cartInfo);
        return "shoppingCartConfirmation";
    }

    @RequestMapping(value = {"/shoppingCartConfirmation"}, method = RequestMethod.POST)
    public String shoppingCartConfirmationSave(HttpServletRequest request, HttpSession session) {
        CartInfo cartInfo = Utils.getCartInSession(request);
        if (cartInfo.isEmpty()) {
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            return "redirect:/shoppingCartCustomer";
        }
        try {
//            int orderNum = orderRepository.getMaxOrder() + 1;
//            Orders orders = new Orders();
//            orders.setId(UUID.randomUUID().toString());
//            orders.setOrderNum(orderNum);
//            orders.setOrderDate(new Date());
//            orders.setAmount(cartInfo.getAmountTotal());
//
//            CustomerInfo customerInfo = new CustomerInfo();
//            orders.setCustomerName(customerInfo.getName());
//            orders.setCustomerEmail(customerInfo.getEmail());
//            orders.setCustomerPhone(customerInfo.getPhone());
//            orders.setCustomerAddress(customerInfo.getAddress());
//            List<CartLineInfo> lines = cartInfo.getCartLine();
//            for (CartLineInfo line : lines) {
//                OrderDetail detail = new OrderDetail();
//                detail.setId(UUID.randomUUID().toString());
//                detail.setOrder(orders);
//                detail.getAmount(line.getAmount());
//                detail.setPrice(line.getProduct().getPrice());
//                detail.getQuanity(line.getQuantity());
//
//                String code = line.getProduct().getCode();
//                Products products = productRepository.findByCode(code);
//                detail.setProduct(products);
//            }
        } catch (Exception e) {
            return "shoppingCartConfirmation";
        }
        Utils.removeCartInSession(request);
        Utils.storeLastOrderedCartInSession(request, cartInfo);
        return "redirect:/shoppingCartFinalize";
    }

    @RequestMapping(value = {"/shoppingCartFinalize"}, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {

        CartInfo lastOrderedCart = Utils.getLastCartInSession(request);
        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }
        model.addAttribute("lastOrderedCart", lastOrderedCart);
        return "shoppingCartFinalize";
    }

    @RequestMapping(value = {"/productImage"}, method = RequestMethod.GET)
    public void productImage(HttpServletResponse response,
                             @RequestParam("code") String code) throws IOException {
        Products productsEntity = null;
        if (code != null) {
            productsEntity = productRepository.findByCode(code);
        }
        if (productsEntity != null && productsEntity.getImage() != null) {
            response.setContentType("image/jpeg,image/jpg,image/png,image/gif");
            response.getOutputStream().write(productsEntity.getImage());
        }
        response.getOutputStream().close();
    }


}
