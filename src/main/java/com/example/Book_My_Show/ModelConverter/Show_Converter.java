package com.example.Book_My_Show.ModelConverter;

import com.example.Book_My_Show.entities.Show;
import com.example.Book_My_Show.requestDtos.ShowDto;

public class Show_Converter {
    public static Show ShowDtoToShow(ShowDto showDto){
        Show show=Show.builder().showDate(showDto.getShowDate()).showType(showDto.getShowType())
                .showTime(showDto.getShowTime())
                .build();
        return show;
    }
}
