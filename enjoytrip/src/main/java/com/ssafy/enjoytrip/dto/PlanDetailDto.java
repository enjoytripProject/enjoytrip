package com.ssafy.enjoytrip.dto;

import com.ssafy.enjoytrip.model.AttractionInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlanDetailDto {

    private String plan_name;
//    private Long user_id;
    private List<List<AttractionInfo>> contendIdList;

}
