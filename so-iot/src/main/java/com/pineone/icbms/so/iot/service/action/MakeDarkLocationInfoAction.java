package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.LocationInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * get Location information that has Under optimum Luminosity <BR/>
 * Created by Melvin on 2016. 1. 20..
 */
public class MakeDarkLocationInfoAction extends DefaultAction {

    private final Logger log = LoggerFactory.getLogger(MakeDarkLocationInfoAction.class);

    DefaultLocation location;

    @Override
    public void execute(IGenericContext context) {

        LocationInfoProvider locationInfoProvider = new LocationInfoProvider();

        location = ((DefaultLocation) context.getValue(IotServiceContext.ACTION_TARGET_LOCATION));

        /**
         * Use LocationInfoProvider that has SDA Interface(get dark location info) <BR/>
         * add Information of Location to ACTION_TARGET_LOCATION  <BR/>
         */

        List<DefaultLocation> locationList = locationInfoProvider.getDarkLocationInfo(location);

        for(DefaultLocation location : locationList){
            log.info(" >> deviceList : " + location.getUri());
        }

        context.addValue(IotServiceContext.ACTION_TARGET_LOCATION, locationList);

    }
}
