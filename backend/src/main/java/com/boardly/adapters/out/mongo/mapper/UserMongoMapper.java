package com.boardly.adapters.out.mongo.mapper;

import com.boardly.adapters.out.mongo.document.UserDocument;
import com.boardly.domain.model.User;

public class UserMongoMapper {
    public static UserDocument toDocument(User user) {
        return new UserDocument(
                user.getId(), user.getName(), user.getEmail(),
                user.getPassword(), user.getRole(), user.getAvatarUrl()
        );
    }
    public static User toDomain(UserDocument doc) {
        return new User(
                doc.getId(), doc.getName(), doc.getEmail(),
                doc.getPassword(), doc.getRole(), doc.getAvatarUrl()
        );
    }
}
