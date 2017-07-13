package com.pearson.aimswebplus.mokitopoc;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import javax.transaction.Transactional;

/**
 * Created by brunoselva on 7/13/17.
 */
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
public class DbUnitTests extends MokitopocApplicationTests{

    public static final String SAMPLE_DATA="classpath:sampleData.xml";

    @Test
    public void contextLoads() {
    }

}
