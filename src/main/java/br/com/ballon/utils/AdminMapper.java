package br.com.ballon.utils;

import br.com.ballon.application.admin.DataAdmin;
import br.com.ballon.application.admin.GetDataAdmin;
import br.com.ballon.domain.user.Admin;
import br.com.ballon.domain.user.Profile;
import br.com.ballon.infra.user.AdminEntity;

import java.util.UUID;

public class AdminMapper {
    public static Admin toDomainByRegisterDto(GetDataAdmin data) {
        return new Admin.AdminUserBuilder()
                .withProfile(Profile.ADMIN)
                .withName(data.name())
                .withEmail(data.email())
                .withPassword(data.password())
                .build();
    }

    public static AdminEntity toEntity(Admin admin) {
        return new AdminEntity(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getProfile(),
                admin.isDeleted(),
                null,
                null,
                null
        );
    }

    public static DataAdmin toDto(AdminEntity adminEntity) {
        return new DataAdmin(adminEntity.getId(), adminEntity.getFullName(), adminEntity.getEmail());

    }

    public static DataAdmin fromGetDataConsumerToDataConsumerUpdate(UUID id, GetDataAdmin data) {
        return new DataAdmin(id, data.name(), data.email());
    }

}
