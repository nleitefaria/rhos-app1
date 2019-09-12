package com.mycompany.rhosapp1.manager;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.mycompany.rhosapp1.service.PingService;

@Stateless
public class PingControl {

    @Inject
    private PingService pingService;

    public String echo( String message ) {
        return pingService.echo(message);
    }

    public String reverse( String message ) {
        return pingService.reverse(message);
    }
}
