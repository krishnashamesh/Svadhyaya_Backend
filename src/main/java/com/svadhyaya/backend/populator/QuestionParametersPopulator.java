package com.svadhyaya.backend.populator;

import com.svadhyaya.backend.db.models.QuestionParametersModel;
import com.svadhyaya.backend.models.data.QuestionParametersData;
import com.svadhyaya.backend.populator.template.Populator;
import org.springframework.stereotype.Service;

@Service
public class QuestionParametersPopulator implements Populator<QuestionParametersModel, QuestionParametersData> {

    @Override
    public void populate(QuestionParametersModel questionParametersModel, QuestionParametersData questionParametersData) {
        questionParametersData.setQuestionId(questionParametersModel.getQuestionId());
    }
}
