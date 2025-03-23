package in.aceinvesting.parent.service.impl;

import in.aceinvesting.parent.dto.UserAccessSource;
import in.aceinvesting.parent.entity.UserAccessSourceEntity;
import in.aceinvesting.parent.mapper.UserAccessSourceMapper;
import in.aceinvesting.parent.repository.UserAccessSourceRepo;
import in.aceinvesting.parent.service.UserAccessSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: Kush Gudhka
 */
@Service
@Slf4j
public class UserAccessSourceServiceImpl implements UserAccessSourceService {

    private final UserAccessSourceRepo userAccessSourceRepo;
    private final UserAccessSourceMapper userAccessSourceMapper;

    public UserAccessSourceServiceImpl(UserAccessSourceRepo userAccessSourceRepo, UserAccessSourceMapper userAccessSourceMapper) {
        this.userAccessSourceRepo = userAccessSourceRepo;
        this.userAccessSourceMapper = userAccessSourceMapper;
    }

    @Override
    public Boolean saveUserAccessSource(UserAccessSource userAccessSource) {
        if (userAccessSource == null) {
            log.error("INFINITI: User Access Source is null.");
            return false;
        }

        UserAccessSourceEntity userAccessSourceEntity = userAccessSourceMapper.toEntity(userAccessSource);
        log.info("INFINITI: Saving UserAccessSource.........");
        userAccessSourceRepo.save(userAccessSourceEntity);
        log.info("INFINITI: Successfully saved UserAccessSource.");
        return true;
    }

    @Override
    public UserAccessSource findByPan(String pan) {
        if(pan == null || pan.isEmpty()) {
            log.error("INFINITI: Pan is null or empty.");
            return null;
        }
        UserAccessSourceEntity userAccessSourceEntity = userAccessSourceRepo.findByPanNumber(pan);
        return userAccessSourceEntity != null ? userAccessSourceMapper.toDTO(userAccessSourceEntity) : null;
    }
}