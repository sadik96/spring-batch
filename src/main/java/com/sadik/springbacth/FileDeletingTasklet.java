package com.sadik.springbacth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.io.File;

public class FileDeletingTasklet implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDeletingTasklet.class);
    private File file;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        boolean deleted = file.delete();
        LOGGER.info("FILE DELETED STATUS:" + deleted);
        if(!deleted){
            throw new UnexpectedJobExecutionException("Could not delete file " + file.getPath());
        }
        return RepeatStatus.FINISHED;
    }

    public void setResources(File file) {
        this.file = file;
    }


}

