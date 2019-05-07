//package com.thanhcong.shop.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.social.UserIdSource;
//import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
//import org.springframework.social.config.annotation.EnableSocial;
//import org.springframework.social.config.annotation.SocialConfigurer;
//import org.springframework.social.connect.ConnectionFactoryLocator;
//import org.springframework.social.connect.UsersConnectionRepository;
//import org.springframework.social.facebook.connect.FacebookConnectionFactory;
//import org.springframework.social.twitter.api.Twitter;
//import org.springframework.social.twitter.connect.TwitterConnectionFactory;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableSocial
//@PropertySource("classpath:social-cfg.properties")
//public class SocialConfig implements SocialConfigurer {
//    private boolean autoSignUp = false;
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfigurer, Environment env) {
//        try {
//            this.autoSignUp = Boolean.parseBoolean(env.getProperty("social.auto-signup"));
//        } catch (Exception e) {
//            this.autoSignUp = false;
//        }
//        TwitterConnectionFactory tfFactory = new TwitterConnectionFactory(env.getProperty("twitter.comnsumer.key"),
//                env.getProperty("twitter.consumer.secret"));
//        cfConfigurer.addConnectionFactory(tfFactory);
//        FacebookConnectionFactory ffactory = new FacebookConnectionFactory(//
//                env.getProperty("facebook.app.id"), //
//                env.getProperty("facebook.app.secret"));
//
//        ffactory.setScope(env.getProperty("facebook.scope"));
//
//        // auth_type=reauthenticate
//
//        cfConfigurer.addConnectionFactory(ffactory);
////         gfactory = new GoogleConnectionFactory(//
////                env.getProperty("google.client.id"), //
////                env.getProperty("google.client.secret"));
////
////        gfactory.setScope(env.getProperty("google.scope"));
////
////        cfConfig.addConnectionFactory(gfactory);
//    }
//
//
//    @Override
//    public UserIdSource getUserIdSource() {
//        return null;
//    }
//
//    @Override
//    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//        return null;
//    }
//
//    @Autowired
//}
