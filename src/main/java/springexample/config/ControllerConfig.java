package springexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springexample.controller.*;

@Configuration
public class ControllerConfig {

    @Bean
    public RegisterController registerController() {
        return new RegisterController();
    }

    @Bean
    public SurveyController surveyController() {
        return new SurveyController();
    }

    @Bean
    public LoginController loginController() {
        return new LoginController();
    }

    @Bean
    public LogoutController logoutController() {
        return new LogoutController();
    }

    @Bean
    public ChangePasswordController changePasswordController() {
        return new ChangePasswordController();
    }

    @Bean
    public MemberListController memberListController() {
        return new MemberListController();
    }

    @Bean
    public MemberDetailController memberDetailController() {
        return new MemberDetailController();
    }

    @Bean
    public RestMemberController restApi() {
        return new RestMemberController();
    }
}
