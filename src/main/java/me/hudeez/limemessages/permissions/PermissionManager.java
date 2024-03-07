package me.hudeez.limemessages.permissions;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.Node;

import java.util.UUID;


public class PermissionManager {

    private static final LuckPerms luckPerms = LuckPermsProvider.get();

    public static void addPermission (UUID userID, String permission, boolean value) {
        luckPerms.getUserManager().modifyUser(userID, user -> {
            Node node = Node.builder(permission).value(value).build();
            user.data().add(node);
        });
    }

    public static void removePermission(UUID userID, String permission) {
        luckPerms.getUserManager().modifyUser(userID, user -> {
            Node node = Node.builder(permission).build();
            user.data().remove(node);
        });
    }
}
