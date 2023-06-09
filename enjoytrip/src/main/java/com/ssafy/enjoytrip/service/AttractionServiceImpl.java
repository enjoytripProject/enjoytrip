package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.AttractionRankingDto;
import com.ssafy.enjoytrip.dto.MyPlanListsDto;
import com.ssafy.enjoytrip.dto.PlanDetailDto;
import com.ssafy.enjoytrip.dto.PlanRankingDto;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Plan;
import com.ssafy.enjoytrip.model.User;
import com.ssafy.enjoytrip.repository.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService{
    private final AttractionRepository attractionRepository;
    public List<AttractionInfo> getAllAttraction(){
        return attractionRepository.getAllAttraction();
    }

    public AttractionInfo getDetailAttraction(long content_id){
        return attractionRepository.getDetailAttraction(content_id);
    }

    public List<AttractionInfo> getSidoGugunAttraction(int sido, int gugun){
        return attractionRepository.getSidoGugunAttraction(sido,gugun);
    }

    public List<AttractionInfo> getTypeAttraction(long type){
        return attractionRepository.getTypeAttraction(type);
    }

//    public void like(int plan_id){
//        attractionRepository.like(plan_id);
//    }
    public void insertlikes(int plan_id, Long user_id){
        attractionRepository.insertlikes(plan_id,user_id);
    }

    public void deletelikes(int plan_id, Long user_id){
        attractionRepository.deletelikes(plan_id, user_id);
    }

    public Long LikeCnt(@PathVariable int plan_id){
        return attractionRepository.LikeCnt(plan_id);
    }

    public Long checkLike(Long user_id, int plan_id){return  attractionRepository.checkLike(user_id,plan_id);}

    public String planRank(int plan_id){return attractionRepository.planRank(plan_id);}




//    public Plan insertPlan(String plan_name, Long user_id){
//        return attractionRepository.insertPlan(plan_name,user_id);
//    }

    public void insertDetailPlan(PlanDetailDto planDetailDto){
        attractionRepository.insertDetailPlan(planDetailDto);
    }


    public void deletePlan(String plan_name, Long user_id){
        attractionRepository.deletePlan(plan_name,user_id);
    }

    public PlanDetailDto selectPlanList(int plan_id){
        return  attractionRepository.selectPlanList(plan_id);
    }

    public void updatePlanList(int plan_id, PlanDetailDto planDetailDto){
        attractionRepository.updatePlanList(plan_id,planDetailDto);
    }

    public List<MyPlanListsDto> myplanLists(String username) {
       return attractionRepository.myplanLists(username);
    }

    public List<PlanRankingDto> getAllPlanLists(){
        return attractionRepository.getAllPlanLists();
    }

    public List<MyPlanListsDto> getNewAllPlanLists(){
        return  attractionRepository.getNewAllPlanLists();
    }

    public List<AttractionInfo> LikeAttractionList(int page){
        return  attractionRepository.LikeAttractionList(page);
    }

    public String getDescription(Long content_id) {
        return attractionRepository.getDescription(content_id);
    }
    public List<AttractionRankingDto> getAttractionRanking() { return attractionRepository.getAttractionRanking();}
}