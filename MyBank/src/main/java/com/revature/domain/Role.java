package com.revature.domain;

import java.util.Arrays;

public enum Role {

    ADMIN("ADMIN"),
    BASIC("BASIC"),
    PREMIUM("PREMIUM"),
    LOCKED("LOCKED");

    private String roleName;

    // enum constructors are implicitly private
    Role(String name) {
        this.roleName = name;
    }

    public static Role getByName(String name) {

        // functional implementation of the above code
        return Arrays.stream(Role.values())
                .filter(role -> role.roleName.equals(name))
                .findFirst()
                .orElse(LOCKED);

    }

    @Override
    public String toString() {
        return roleName;
    }

    }


