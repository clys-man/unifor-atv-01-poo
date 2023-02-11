package com.clysman.Unifor.Tests;

import com.clysman.Unifor.Band.Band;
import com.clysman.Unifor.Band.BandReport;
import com.clysman.Unifor.Band.BandRespository;

public class BandStatisticTest {
    public static void main(String[] args) {
        BandRespository bandRespository = new BandRespository();
        bandRespository.add(new Band("meio", "Indie", 3, 1234.50, 6));
        bandRespository.add(new Band("maior", "rock", 5, 1234.99, 3));
        bandRespository.add(new Band("menor", "rock", 4, 1234, 2));

        BandReport bandReport = new BandReport(bandRespository);
        bandReport.generate();
    }
}
