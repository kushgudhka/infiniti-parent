package in.aceinvesting.parent.controller;

import in.aceinvesting.parent.dto.UserAccessSource;
import in.aceinvesting.parent.entity.ResponseEntity;
import in.aceinvesting.parent.service.UserAccessSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Kush Gudhka
 */

@RestController
@RequestMapping("/userAccessSource")
@Slf4j
public class UserAccessSourceController {

    private final UserAccessSourceService userAccessSourceService;

    public UserAccessSourceController(UserAccessSourceService userAccessSourceService) {
        this.userAccessSourceService = userAccessSourceService;
    }

    @PostMapping("/saveUserAccessSource")
    public ResponseEntity saveUserAccessSource(@RequestBody UserAccessSource userAccessSource) {

        UserAccessSource validUser = userAccessSourceService.findByPan(userAccessSource.getPanNumber());
        if(validUser != null){
            return new ResponseEntity(validUser.getStatus().getStatusName());
        }

        Boolean result = userAccessSourceService.saveUserAccessSource(userAccessSource);
        if (result.equals(Boolean.TRUE)) {
            validUser = userAccessSourceService.findByPan(userAccessSource.getPanNumber());
            return new ResponseEntity(validUser.getUserId(), validUser.getUsername(), validUser.getStatus(), "SUCCESS");
        } else {
            log.error("INFINITI: Error while saving user access source for PAN : {}", userAccessSource.getPanNumber());
            return new ResponseEntity("FAILED TO SAVE THE DATA..");
        }

    }
}
