package com.app.bank.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration



@OpenAPIDefinition(

        info = @Info(
                title = "BankApp",
                description = "BankApp is a small bank application which allows: user creation, account creation/delete, card creation/delete," +
                        "withdraw in the bank cask desk/pos, deposit money in the bank cash desk, transfer money only between declared accounts," +
                        "block/unblock (set ACTIVE status) cards, change pin  ",
               // license = @License(name = "Apache Licence 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"),
                version = "1.0"


        )

)

@SecurityScheme(
        name = "http_basic",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)





public class OpenAPIConfig {

}
