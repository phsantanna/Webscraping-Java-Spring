package com.webscrapping.project.users;

import jakarta.validation.constraints.NotBlank;

public record RegisterDto(@NotBlank String login, @NotBlank String senha, UserRoles role) {
}
