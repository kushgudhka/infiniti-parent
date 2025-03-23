package in.aceinvesting.parent.service;

import in.aceinvesting.parent.dto.UserAccessSource;

/**
 * @Author: Kush Gudhka
 */
public interface UserAccessSourceService {

    //To Save User Details
    Boolean saveUserAccessSource(UserAccessSource userAccessSource);

    UserAccessSource findByPan(String pan);
}
