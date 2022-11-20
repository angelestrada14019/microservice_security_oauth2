package com.dh.usersms.repository;

import com.dh.usersms.dto.UserDtoResponseNotAdmin;
import com.dh.usersms.model.User;
import org.keycloak.admin.client.Keycloak;

import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class KeyCloakUserRepository implements IUserRepository {
    @Autowired
    private Keycloak keycloakClient;

    @Value("${dh.keycloak.realm}")
    private String realm;

    @Override
    public List<User> findByFirstName(String name) {
        List<UserRepresentation> users = keycloakClient.realm(realm).users().search(name);
        return users.stream().map(this::toUser).collect(Collectors.toList());
    }
    @Override
    public List<User> findByUsername(String username) {
        List<UserRepresentation> users = keycloakClient.realm(realm).users().search(username);
        return users.stream().map(this::toUser).collect(Collectors.toList());
    }

    @Override
    public User findById(String id) {
        UserRepresentation user = keycloakClient.realm(realm).users().get(id).toRepresentation();
        return toUser(user);
    }

    @Override
    public User updateNationality(String id, String nationality) {
        UserResource userResource = keycloakClient.realm(realm).users().get(id);
        UserRepresentation user = userResource.toRepresentation();
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("nacionalidad", List.of(nationality));
        user.setAttributes(attributes);
        userResource.update(user);
        return toUser(user);
    }
    @Override
    public User updateUser(User user) {
        UserResource userResource = keycloakClient.realm(realm).users().get(user.getId());
        UserRepresentation userRepresentation = userResource.toRepresentation();
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("nacionalidad", List.of(user.getNationality()));
        userRepresentation.setAttributes(attributes);
        userResource.update(userRepresentation);
        return toUser(userRepresentation);
    }

    @Override
    public List<UserDtoResponseNotAdmin> findUserNotAdmin() {
        List<UserRepresentation> userRepresentations = keycloakClient.realm(realm).users().list();
        List<UserRepresentation> userNotAdmin =userRepresentations.stream()
                .filter(user ->
                        !user.getGroups().contains("/admin"))
                .collect(Collectors.toList());
        return userNotAdmin.stream().map(this::toUserDtoResponseNotAdmin).collect(Collectors.toList());
    }



    private User toUser(UserRepresentation userRepresentation) {
        String nationality = null;
        try {
            nationality = userRepresentation.getAttributes().get("nacionalidad").get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User(userRepresentation.getId(), userRepresentation.getUsername(), userRepresentation.getEmail(), userRepresentation.getFirstName(), userRepresentation.getLastName(), nationality);
    }

    private UserDtoResponseNotAdmin toUserDtoResponseNotAdmin(UserRepresentation userRepresentation) {
        return new UserDtoResponseNotAdmin(userRepresentation.getUsername(), userRepresentation.getEmail());
    }
}
