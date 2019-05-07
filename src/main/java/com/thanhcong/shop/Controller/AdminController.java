package com.thanhcong.shop.Controller;

import com.thanhcong.shop.Repository.OrderRepository;
import com.thanhcong.shop.Repository.ProductRepository;
import com.thanhcong.shop.Service.OrderService;
import com.thanhcong.shop.Service.ProductService;
import com.thanhcong.shop.form.ProductForm;
import com.thanhcong.shop.model.Products;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminController {
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderRepository;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @RequestMapping(value = {"/accountInfo"}, method = RequestMethod.GET)
    public String accountInfo(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());
        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }

    @RequestMapping(value = {"/admin/orderList"}, method = RequestMethod.GET)
    public String orderList(Model model,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);

        model.addAttribute("listOrder", orderRepository.findAll(pageable));
        return "orderList";
    }

    @RequestMapping(value = {"/admin/product"}, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        ProductForm productForm = null;
        if (code != null && code.length() > 0) {
            Products productsEntity = productService.findByCode(code);
            if (productsEntity != null) {
                productForm = new ProductForm(productsEntity);
            }
        }
        if (productForm == null) {
            productForm = new ProductForm();
            productForm.setNewProduct(true);
        }
        model.addAttribute("productForm", productForm);
        return "product";
    }

    @RequestMapping(value = {"/admin/product"}, method = RequestMethod.POST)
    public String productSave(@ModelAttribute("productForm") @Validated ProductForm productForm,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "product";
        }
        try {
            productService.update(productForm);
        } catch (Exception e) {
            return "product";
        }
        return "redirect:/productList";
    }

}
