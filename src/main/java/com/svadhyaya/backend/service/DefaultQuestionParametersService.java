package com.svadhyaya.backend.service;

import com.svadhyaya.backend.db.models.QuestionParametersModel;
import com.svadhyaya.backend.db.models.RoundResultDetailsModel;
import com.svadhyaya.backend.db.models.SimulationEntryModel;
import com.svadhyaya.backend.db.models.SimulationModel;
import com.svadhyaya.backend.repository.QuestionParametersRepository;
import com.svadhyaya.backend.repository.RoundResultDetailsRepository;
import com.svadhyaya.backend.repository.SimulationEntryRepository;
import com.svadhyaya.backend.repository.SimulationRepository;
import com.svadhyaya.backend.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DefaultQuestionParametersService {

    @Autowired
    private MathUtil mathUtil;

    @Autowired
    private SimulationRepository simulationRepository;

    @Autowired
    private SimulationEntryRepository simulationEntryRepository;

    @Autowired
    private QuestionParametersRepository questionParametersRepository;

    @Autowired
    private RoundResultDetailsRepository roundResultDetailsRepository;

    public QuestionParametersModel setQuestionParameters(QuestionParametersModel questionParameters) {

        int minMeanValue = 10, maxMeanValue = 10000, varianceSpreadPercent = 20;

        BigDecimal meanQuantity = mathUtil.getRandomValueInRange(minMeanValue, maxMeanValue);
        questionParameters.setMeanQuantity(meanQuantity);
        questionParameters.setVarianceQuantity(mathUtil.getVarianceQuantity(meanQuantity, varianceSpreadPercent));

        //Handled the edge case where WSP=RP or RP=SalP or WSP=SalP or
        // RP<SalP
        while (questionParameters.getWholeSalePrice().doubleValue() >= (questionParameters.getRetailPrice().doubleValue())
                || questionParameters.getRetailPrice().doubleValue() <= (questionParameters.getSalvagePrice().doubleValue())
                || questionParameters.getWholeSalePrice().doubleValue() <= (questionParameters.getSalvagePrice().doubleValue())
                || questionParameters.getRetailPrice().doubleValue() <= (questionParameters.getCostPerUnit().doubleValue())
                || questionParameters.getRetailPrice().doubleValue() <= (questionParameters.getCostPerUnit()).doubleValue()) {
            setPricesForQuestion(questionParameters);
        }

        return questionParameters;
    }

    private void setPricesForQuestion(QuestionParametersModel questionParameters) {
        BigDecimal wholeSalePrice = mathUtil.getRandomValueInRange(30, 999);
        questionParameters.setWholeSalePrice(wholeSalePrice);
        BigDecimal retailPrice = MathUtil.round(
                wholeSalePrice.multiply(BigDecimal.ONE
                        .add(mathUtil.getRandomValueInRange(0, 1))), 2);
        questionParameters.setRetailPrice(retailPrice);
        BigDecimal salvagePrice = MathUtil.round(wholeSalePrice
                .multiply(BigDecimal.ONE
                        .subtract(mathUtil.getRandomValueInRange(0, 1))), 2);
        questionParameters.setSalvagePrice(salvagePrice);
        questionParameters.setCostPerUnit(MathUtil.round(retailPrice
                .multiply(BigDecimal.ONE
                        .subtract(mathUtil.getRandomValueInRange(0, 1))), 2));
    }

    public QuestionParametersModel getQuestionParametersModelById(String questionId) {
        return questionParametersRepository.findById(Long.valueOf(questionId)).get();
    }

    public SimulationModel setAnswerForQuestion(SimulationModel simulation, SimulationEntryModel simulationEntryModel, QuestionParametersModel questionParametersModel, String orderQuantity) {

        RoundResultDetailsModel roundResultDetailsModel =
                new RoundResultDetailsModel();

        roundResultDetailsModel.setIterationId(simulationEntryModel.getRoundResultDetailsModels().size() + 1);
        roundResultDetailsModel.setOrderedQuantity(MathUtil.round(BigDecimal.valueOf(Double.valueOf(orderQuantity)), 0));

        BigDecimal meanQuantity = questionParametersModel.getMeanQuantity();
        BigDecimal varianceQuantity =
                questionParametersModel.getVarianceQuantity();
        BigDecimal actualDemand = mathUtil.getActualDemand(meanQuantity,
                varianceQuantity);
        roundResultDetailsModel.setActualDemand(actualDemand);

        BigDecimal retailPrice = questionParametersModel.getRetailPrice();
        BigDecimal costPerUnit = questionParametersModel.getCostPerUnit();
        BigDecimal wholeSalePrice = questionParametersModel.getWholeSalePrice();
        BigDecimal salvagePrice = questionParametersModel.getSalvagePrice();

        roundResultDetailsModel.setRevenue(MathUtil.round(
                retailPrice
                        .multiply(actualDemand.compareTo(roundResultDetailsModel.getOrderedQuantity()) < 0 ? actualDemand : roundResultDetailsModel.getOrderedQuantity()), 2));
        roundResultDetailsModel.setCost(MathUtil.round(
                costPerUnit
                        .multiply(roundResultDetailsModel.getOrderedQuantity()), 2));
        roundResultDetailsModel.setProfitOrLoss(MathUtil.round(
                roundResultDetailsModel.getRevenue()
                        .subtract(roundResultDetailsModel.getCost()), 2));

        //Ideal Values
        BigDecimal idealOrderQuantity =
                mathUtil.getIdealOrderQuantity(wholeSalePrice,
                        retailPrice, salvagePrice, meanQuantity,
                        varianceQuantity);

        roundResultDetailsModel.setIdealOrderedQuantity(MathUtil.round(idealOrderQuantity, 0));

        roundResultDetailsModel.setIdealRevenue(MathUtil.round(
                retailPrice
                        .multiply(actualDemand.compareTo(roundResultDetailsModel.getIdealOrderedQuantity()) < 0 ? actualDemand : roundResultDetailsModel.getIdealOrderedQuantity()), 2));
        roundResultDetailsModel.setIdealCost(MathUtil.round(
                costPerUnit
                        .multiply(roundResultDetailsModel.getIdealOrderedQuantity()), 2));
        roundResultDetailsModel.setIdealProfitOrLoss(MathUtil.round(
                roundResultDetailsModel.getIdealRevenue()
                        .subtract(roundResultDetailsModel.getIdealCost()),
                2));

        roundResultDetailsModel.setSimulationEntry(simulationEntryModel);
        roundResultDetailsRepository.saveAndFlush(roundResultDetailsModel);
        roundResultDetailsRepository.refresh(roundResultDetailsModel);
        // List<RoundResultDetailsModel> roundResultDetailsModels =
        //       new ArrayList<>(simulationEntryModel
        //       .getRoundResultDetailsModels());
        // roundResultDetailsModels.add(roundResultDetailsModel);
        // simulationEntryModel.setRoundResultDetailsModels
        // (roundResultDetailsModels);

        // simulationEntryRepository.saveAndFlush(simulationEntryModel);

        simulationEntryRepository.refresh(simulationEntryModel);
        simulationRepository.refresh(simulation);

        return simulation;

    }
}
