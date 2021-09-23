package com.svadhyaya.backend.populator;

import com.svadhyaya.backend.db.models.RoundResultDetailsModel;
import com.svadhyaya.backend.models.data.RoundResultDetailsData;
import com.svadhyaya.backend.populator.template.Populator;
import org.springframework.stereotype.Service;

@Service
public class RoundResultDetailsPopulator implements Populator<RoundResultDetailsModel, RoundResultDetailsData> {
    @Override
    public void populate(RoundResultDetailsModel roundResultDetailsModel, RoundResultDetailsData roundResultDetailsData) {
        roundResultDetailsData.setRoundResultDetailsId(roundResultDetailsModel.getRoundResultDetailsId());
    }
}
