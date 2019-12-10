package br.com.central.erros.impl.business.repository.V1;


import br.com.central.erros.impl.business.entity.V1.UserV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserV1, Integer> {

	//	@Transactional(readOnly=true)
	UserV1 findByEmail(String email);
	Boolean existsByEmail(String email);
}
