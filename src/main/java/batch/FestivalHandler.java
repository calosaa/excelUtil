package batch;

import java.util.ArrayList;
import java.util.List;

public class FestivalHandler {
    private static final String[] holiday_newYear = {"1-1","1-2","1-3"};
    private static final String[] holiday_spring = {"1-31","2-1","2-2","2-3","2-4","2-5","2-6"};
    private static final String[] work_spring = {"1-29","1-30"};

    private ArrayList<String> holiday = new ArrayList<>();
    private ArrayList<String> work = new ArrayList<>();
    public FestivalHandler(){
        holiday.addAll(List.of(holiday_newYear));
        holiday.addAll(List.of(holiday_spring));
        work.addAll(List.of(work_spring));
    }

    public boolean getRest(String day){
        if(holiday.contains(day)) return true;
        else if (work.contains(day)) return false;
        else return false;
    }
}
