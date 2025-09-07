package com.boardly.adapters.out.mongo.document;

import com.boardly.domain.model.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDocument {
    @Id
    private String id;

    private String name;

    @Indexed(unique = true) // e-mail único no banco
    private String email;
    private String password;
    private Role role;
    private String avatarUrl;
}
