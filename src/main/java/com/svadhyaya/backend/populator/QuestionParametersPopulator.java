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

        questionParametersData.setMeanQuantity(questionParametersModel.getMeanQuantity());
        questionParametersData.setVarianceQuantity(questionParametersModel.getVarianceQuantity());

        questionParametersData.setWholeSalePrice(questionParametersModel.getWholeSalePrice());
        questionParametersData.setRetailPrice(questionParametersModel.getRetailPrice());
        questionParametersData.setSalvagePrice(questionParametersModel.getSalvagePrice());
        questionParametersData.setCostPerUnit(questionParametersModel.getCostPerUnit());
        questionParametersData.setRevenueSharingPercentage(questionParametersModel.getRevenueSharingPercentage());
        questionParametersData.setUnitOptionsExercisePrice(questionParametersModel.getUnitOptionsExercisePrice());
        questionParametersData.setUnitOptionsPrice(questionParametersModel.getUnitOptionsPrice());

    }
}
