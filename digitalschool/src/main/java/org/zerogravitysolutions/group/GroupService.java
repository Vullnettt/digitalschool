package org.zerogravitysolutions.group;

import org.springframework.http.ResponseEntity;

public interface GroupService {
    ResponseEntity<GroupDto> save(GroupDto groupDto);
}
