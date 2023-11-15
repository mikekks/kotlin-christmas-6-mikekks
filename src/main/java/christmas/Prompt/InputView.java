package christmas.Prompt;

import static christmas.global.ErrorMessage.*;
import static christmas.global.GuildMessage.*;

import camp.nextstep.edu.missionutils.Console;
import christmas.global.enums.Menu;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class InputView {

    public static LocalDate inputVisitDate(){
        System.out.println(REQUEST_VISIT_DATE);
        String inputDate = Console.readLine();
        LocalDate visitDate = null;

        try{ // validation
            Integer date = Integer.parseInt(inputDate);
            visitDate = LocalDate.of(2023, 12, date);
        }catch(IllegalArgumentException e){
            System.out.println(INVALID_DATE_FORMAT);
            visitDate = inputVisitDate();
        }

        return visitDate;
    }

    public static Map<String, Integer> inputOrder(){
        Map<String, Integer> menuList = new HashMap<>();
        System.out.println(REQUEST_MENU_COUNT);
        String order = Console.readLine();
        try{
            validateMenuList(order, menuList);
        }catch (IllegalArgumentException e){
            System.out.println(INVALID_ORDER_FORMAT);
            menuList = inputOrder();
        }

        return menuList;
    }


    private static void validateMenuList(String order, Map<String, Integer> menuList){
        String[] split = order.split(",");
        for(String s : split){
            if(!s.contains("-")){
                throw new IllegalArgumentException();
            }

            String[] menu = s.split("-");

            if(menuList.containsKey(menu[0])){
                throw new IllegalArgumentException();
            }

            if(Menu.getMenu(menu[0]) == null){
                throw new IllegalArgumentException();
            }
            menuList.put(menu[0], Integer.parseInt(menu[1]));
        }
    }
}
