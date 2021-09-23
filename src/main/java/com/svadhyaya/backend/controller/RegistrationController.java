package com.svadhyaya.backend.controller;

import com.svadhyaya.backend.db.models.UserModel;
import com.svadhyaya.backend.db.models.enums.RolesEnum;
import com.svadhyaya.backend.models.data.DefaultResponseData;
import com.svadhyaya.backend.models.data.ErrorResponseData;
import com.svadhyaya.backend.models.request.AuthenticationRequest;
import com.svadhyaya.backend.service.DefaultRoleService;
import com.svadhyaya.backend.service.DefaultUserDetailsService;
import com.svadhyaya.backend.validators.AuthenticationRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class RegistrationController {

    @Autowired
    private DefaultUserDetailsService userDetailsService;

    @Autowired
    private DefaultRoleService defaultRoleService;

    @Autowired
    private AuthenticationRequestValidator authenticationRequestValidator;

    @InitBinder("authenticationRequest")
    public void initMerchantOnlyBinder(WebDataBinder binder) {
        binder.addValidators(authenticationRequestValidator);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@Valid @RequestBody AuthenticationRequest authenticationRequest,
                                          Errors errors) throws Exception {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new ErrorResponseData(errors.getAllErrors()
                    .stream().map(er -> er.getCode()).collect(Collectors.toList()).toString()));
        }

        if (Objects.isNull(userDetailsService.getUserFromRepoWithName(authenticationRequest.getUserName()))) {
            try {
                userDetailsService.save(new UserModel(authenticationRequest.getUserName(),
                        authenticationRequest.getPassword(), new ArrayList<>(),
                        Stream.of(defaultRoleService.findRoleByName(RolesEnum.USER.toString())).collect(Collectors.toSet())));
                return ResponseEntity.ok(new DefaultResponseData("Registration Successful"));
            } catch (Exception exception) {
                return ResponseEntity.badRequest().body(new ErrorResponseData(exception.getMessage()));
            }
        } else {
            return ResponseEntity.badRequest().body(new ErrorResponseData("UserModel already exists"));
        }
    }

    @PostMapping("/createRoles")
    public ResponseEntity<?> createAllRoles() {
        RolesEnum[] rolesEnums = defaultRoleService.createRoles();
        return ResponseEntity.ok(new DefaultResponseData("Roles created : " + rolesEnums));
    }
}
