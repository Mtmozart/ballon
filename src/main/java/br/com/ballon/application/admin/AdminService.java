package br.com.ballon.application.admin;

import br.com.ballon.domain.exception.BallonException;
import br.com.ballon.domain.user.Admin;
import br.com.ballon.infra.user.AdminEntity;
import br.com.ballon.infra.user.AdminEntityRepository;
import br.com.ballon.utils.AdminMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Locale;
import java.util.UUID;

@Service
public class AdminService implements IAdmin<Admin, UUID, GetDataAdmin, DataAdmin>{

    private final AdminEntityRepository adminEntityRepository;

    public AdminService(AdminEntityRepository adminEntityRepository) {
        this.adminEntityRepository = adminEntityRepository;
    }

    @Override
    public DataAdmin create(Admin admin) {
        AdminEntity adminEntity = this.adminEntityRepository.save(AdminMapper.toEntity(admin));
        return AdminMapper.toDto(adminEntity);
    }

    @Override
    public DataAdmin update(UUID id, GetDataAdmin getDataAdmin) {
        this.adminEntityRepository.findById(id).orElseThrow(() -> new BallonException("Usuário não encontrado"));
        this.adminEntityRepository.updatesEntity(
                getDataAdmin.name(),
                getDataAdmin.email(),
                id,
                Instant.now()
        );
        return AdminMapper.fromGetDataConsumerToDataConsumerUpdate(id, getDataAdmin);
    }

    @Override
    public void delete(UUID id) {
      AdminEntity admin =  this.adminEntityRepository.findById(id).orElseThrow(() -> new BallonException("Usuário não encontrado"));
      boolean isDelete = !admin.getIsDeleted();
      if(isDelete){
          this.adminEntityRepository.deleteAdmin(id, true, Instant.now());
      } else  {
          this.adminEntityRepository.deleteAdmin(id, false, null);
      }
    }

    @Override
    public DataAdmin findById(UUID id) {
        return AdminMapper.toDto(this.adminEntityRepository.findById(id).orElseThrow(() -> new BallonException("Usuário não encontrado")));
    }
}
