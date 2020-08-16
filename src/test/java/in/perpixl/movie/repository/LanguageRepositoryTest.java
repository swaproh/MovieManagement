package in.perpixl.movie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import in.perpixl.movie.entity.LanguageEntity;

/**
 * @author w10rohit
 * @DataJpaTest sets up spring application context.
 * The so created application context will not contain the whole context 
 * needed for our Spring Boot application, but instead only a “slice” of it 
 * containing the components needed to initialize any JPA-related components 
 * like our Spring Data repository.
 * 
 * Note that by default the application context containing all these components, 
 * including the in-memory database, is shared between all test methods within 
 * all @DataJpaTest-annotated test classes.

	This is why, by default, each test method runs in its own transaction, 
	which is rolled back after the method has executed. This way, the database 
	state stays pristine between tests and the tests stay independent of each other.
 */
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase
public class LanguageRepositoryTest {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private LanguageRepository repo;
	
	//@Test
	public void injectedComponentsAreNotNull()
	{
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(testEntityManager).isNotNull();
		assertThat(repo).isNotNull();
	}
	//@Test
	public void saveTest()
	{
		LanguageEntity entity = new LanguageEntity();
		entity.setName("Hindi");
		LanguageEntity savedEntity = testEntityManager.persistAndFlush(entity);
		assertThat(savedEntity.getId()).isNotNull();
		assertThat(repo.findById(savedEntity.getId()).get()).isEqualTo(savedEntity);
	}
	
	//@Test
	public void updateTest()
	{
		LanguageEntity entity = new LanguageEntity();
		entity.setName("Hindi");
		
		// save first
		LanguageEntity savedEntity = testEntityManager.persistAndFlush(entity);
		assertThat(savedEntity.getId()).isNotNull();
		// update now
		Optional<LanguageEntity> getEntityOpt = repo.findById(savedEntity.getId());
		assertThat(getEntityOpt.isPresent()).isTrue();
		
		LanguageEntity ent = getEntityOpt.get();
		ent.setName("Marathi");
		
		LanguageEntity updatedEntity = testEntityManager.persistAndFlush(ent);
		assertThat(updatedEntity.getName()).isEqualTo(ent.getName());
	}
	
	//@Test
	public void readTest()
	{
		LanguageEntity entity = new LanguageEntity();
		entity.setName("Hindi");
		
		// save first
		LanguageEntity savedEntity = testEntityManager.persistAndFlush(entity);
		assertThat(savedEntity.getId()).isNotNull();
		// update now
		Optional<LanguageEntity> getEntityOpt = repo.findById(savedEntity.getId());
		assertThat(getEntityOpt.isPresent()).isTrue();
		assertThat(getEntityOpt.get().getName()).isSameAs(entity.getName());
	}
	
	//@Test
	public void readAllTest()
	{
		LanguageEntity entity1 = new LanguageEntity();
		entity1.setName("Hindi");
		
		LanguageEntity entity2 = new LanguageEntity();
		entity2.setName("Hindi");
		
		// save first
		LanguageEntity savedEntity1 = testEntityManager.persistAndFlush(entity1);
		assertThat(savedEntity1.getId()).isNotNull();
		LanguageEntity savedEntity2 = testEntityManager.persistAndFlush(entity2);
		assertThat(savedEntity2.getId()).isNotNull();
		
		// update now
		List<LanguageEntity> entityList = repo.findAll();
		assertThat(entityList.size()).isEqualTo(2);
	}
	
	//@Test
	public void deleteTest()
	{
		LanguageEntity entity1 = new LanguageEntity();
		entity1.setName("Hindi");
		
		// save first
		LanguageEntity savedEntity1 = testEntityManager.persistAndFlush(entity1);
		assertThat(savedEntity1.getId()).isNotNull();
		// delete now
		repo.deleteById(savedEntity1.getId());
		assertThat(repo.findById(savedEntity1.getId()).isPresent()).isFalse();
	}
}
