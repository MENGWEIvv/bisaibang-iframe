package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.RaceApp;

import com.mycompany.myapp.domain.Iframe;
import com.mycompany.myapp.repository.IframeRepository;
import com.mycompany.myapp.service.IframeService;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the IframeResource REST controller.
 *
 * @see IframeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RaceApp.class)
public class IframeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STAGE = "AAAAAAAAAA";
    private static final String UPDATED_STAGE = "BBBBBBBBBB";

    private static final String DEFAULT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_GROUP = "BBBBBBBBBB";

    private static final Integer DEFAULT_FLAG = 1;
    private static final Integer UPDATED_FLAG = 2;

    private static final Integer DEFAULT_RACE_ID = 1;
    private static final Integer UPDATED_RACE_ID = 2;

    @Autowired
    private IframeRepository iframeRepository;

    @Autowired
    private IframeService iframeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restIframeMockMvc;

    private Iframe iframe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IframeResource iframeResource = new IframeResource(iframeService);
        this.restIframeMockMvc = MockMvcBuilders.standaloneSetup(iframeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Iframe createEntity(EntityManager em) {
        Iframe iframe = new Iframe()
            .name(DEFAULT_NAME)
            .stage(DEFAULT_STAGE)
            .time(DEFAULT_TIME)
            .group(DEFAULT_GROUP)
            .flag(DEFAULT_FLAG)
            .raceId(DEFAULT_RACE_ID);
        return iframe;
    }

    @Before
    public void initTest() {
        iframe = createEntity(em);
    }

    @Test
    @Transactional
    public void createIframe() throws Exception {
        int databaseSizeBeforeCreate = iframeRepository.findAll().size();

        // Create the Iframe
        restIframeMockMvc.perform(post("/api/iframes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(iframe)))
            .andExpect(status().isCreated());

        // Validate the Iframe in the database
        List<Iframe> iframeList = iframeRepository.findAll();
        assertThat(iframeList).hasSize(databaseSizeBeforeCreate + 1);
        Iframe testIframe = iframeList.get(iframeList.size() - 1);
        assertThat(testIframe.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testIframe.getStage()).isEqualTo(DEFAULT_STAGE);
        assertThat(testIframe.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testIframe.getGroup()).isEqualTo(DEFAULT_GROUP);
        assertThat(testIframe.getFlag()).isEqualTo(DEFAULT_FLAG);
        assertThat(testIframe.getRaceId()).isEqualTo(DEFAULT_RACE_ID);
    }

    @Test
    @Transactional
    public void createIframeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = iframeRepository.findAll().size();

        // Create the Iframe with an existing ID
        iframe.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIframeMockMvc.perform(post("/api/iframes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(iframe)))
            .andExpect(status().isBadRequest());

        // Validate the Iframe in the database
        List<Iframe> iframeList = iframeRepository.findAll();
        assertThat(iframeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllIframes() throws Exception {
        // Initialize the database
        iframeRepository.saveAndFlush(iframe);

        // Get all the iframeList
        restIframeMockMvc.perform(get("/api/iframes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(iframe.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].stage").value(hasItem(DEFAULT_STAGE.toString())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].group").value(hasItem(DEFAULT_GROUP.toString())))
            .andExpect(jsonPath("$.[*].flag").value(hasItem(DEFAULT_FLAG)))
            .andExpect(jsonPath("$.[*].raceId").value(hasItem(DEFAULT_RACE_ID)));
    }

    @Test
    @Transactional
    public void getIframe() throws Exception {
        // Initialize the database
        iframeRepository.saveAndFlush(iframe);

        // Get the iframe
        restIframeMockMvc.perform(get("/api/iframes/{id}", iframe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(iframe.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.stage").value(DEFAULT_STAGE.toString()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.group").value(DEFAULT_GROUP.toString()))
            .andExpect(jsonPath("$.flag").value(DEFAULT_FLAG))
            .andExpect(jsonPath("$.raceId").value(DEFAULT_RACE_ID));
    }

    @Test
    @Transactional
    public void getNonExistingIframe() throws Exception {
        // Get the iframe
        restIframeMockMvc.perform(get("/api/iframes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIframe() throws Exception {
        // Initialize the database
        iframeService.update(iframe);

        int databaseSizeBeforeUpdate = iframeRepository.findAll().size();

        // Update the iframe
        Iframe updatedIframe = iframeRepository.findById(iframe.getId()).get();
        // Disconnect from session so that the updates on updatedIframe are not directly saved in db
        em.detach(updatedIframe);
        updatedIframe
            .name(UPDATED_NAME)
            .stage(UPDATED_STAGE)
            .time(UPDATED_TIME)
            .group(UPDATED_GROUP)
            .flag(UPDATED_FLAG)
            .raceId(UPDATED_RACE_ID);

        restIframeMockMvc.perform(put("/api/iframes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIframe)))
            .andExpect(status().isOk());

        // Validate the Iframe in the database
        List<Iframe> iframeList = iframeRepository.findAll();
        assertThat(iframeList).hasSize(databaseSizeBeforeUpdate);
        Iframe testIframe = iframeList.get(iframeList.size() - 1);
        assertThat(testIframe.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testIframe.getStage()).isEqualTo(UPDATED_STAGE);
        assertThat(testIframe.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testIframe.getGroup()).isEqualTo(UPDATED_GROUP);
        assertThat(testIframe.getFlag()).isEqualTo(UPDATED_FLAG);
        assertThat(testIframe.getRaceId()).isEqualTo(UPDATED_RACE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingIframe() throws Exception {
        int databaseSizeBeforeUpdate = iframeRepository.findAll().size();

        // Create the Iframe

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIframeMockMvc.perform(put("/api/iframes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(iframe)))
            .andExpect(status().isBadRequest());

        // Validate the Iframe in the database
        List<Iframe> iframeList = iframeRepository.findAll();
        assertThat(iframeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIframe() throws Exception {
        // Initialize the database
        iframeService.update(iframe);

        int databaseSizeBeforeDelete = iframeRepository.findAll().size();

        // Delete the iframe
        restIframeMockMvc.perform(delete("/api/iframes/{id}", iframe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Iframe> iframeList = iframeRepository.findAll();
        assertThat(iframeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Iframe.class);
        Iframe iframe1 = new Iframe();
        iframe1.setId(1L);
        Iframe iframe2 = new Iframe();
        iframe2.setId(iframe1.getId());
        assertThat(iframe1).isEqualTo(iframe2);
        iframe2.setId(2L);
        assertThat(iframe1).isNotEqualTo(iframe2);
        iframe1.setId(null);
        assertThat(iframe1).isNotEqualTo(iframe2);
    }
}
