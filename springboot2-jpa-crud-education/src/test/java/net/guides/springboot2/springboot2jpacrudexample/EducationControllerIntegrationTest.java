package net.guides.springboot2.springboot2jpacrudexample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import net.guides.springboot2.springboot2jpacrudexample.model.Education;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EducationControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllEducations() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employees",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetEducationById() {
		Education education = restTemplate.getForObject(getRootUrl() + "/employees/1", Education.class);
		System.out.println(education.getFirstName());
		assertNotNull(education);
	}

	@Test
	public void testCreateEducation() {
		Education education = new Education();
		education.setEmailId("admin@gmail.com");
		education.setFirstName("admin");
		education.setLastName("admin");

		ResponseEntity<Education> postResponse = restTemplate.postForEntity(getRootUrl() + "/employees", education, Education.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEducation() {
		int id = 1;
		Education education = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Education.class);
		education.setFirstName("admin1");
		education.setLastName("admin2");

		restTemplate.put(getRootUrl() + "/employees/" + id, education);

		Education updatedEmployee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Education.class);
		assertNotNull(updatedEmployee);
	}

	@Test
	public void testDeleteEducation() {
		int id = 2;
		Education education = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Education.class);
		assertNotNull(education);

		restTemplate.delete(getRootUrl() + "/employees/" + id);

		try {
			education = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Education.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
