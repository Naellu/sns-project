package myproject.sns.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;

import static myproject.sns.domain.member.entity.Permission.*;

@RequiredArgsConstructor
public enum Role {

    ROLE_USER(Collections.emptySet()),
    ROLE_ADMIN(
            Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_DELETE,
            ADMIN_CREATE,
            MANAGER_READ,
            MANAGER_UPDATE,
            MANAGER_DELETE,
            MANAGER_CREATE
            )
    ),
    ROLE_MANAGER(
            Set.of(
            MANAGER_READ,
            MANAGER_UPDATE,
            MANAGER_DELETE,
            MANAGER_CREATE
            )
    );

    @Getter
    private final Set<Permission> permissions;
}
