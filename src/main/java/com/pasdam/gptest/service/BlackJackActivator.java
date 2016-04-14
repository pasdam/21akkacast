package com.pasdam.gptest.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import com.pasdam.gptest.service.impl.InstantiateDealerImpl;

public class BlackJackActivator implements BundleActivator, ServiceListener {

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("[OSGi] <BlackJackActivator::start> Starting bundle");

		context.registerService(InstantiateDealer.class, new InstantiateDealerImpl(), null);
        
        context.addServiceListener(this);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		context.ungetService(context.getServiceReference(InstantiateDealer.class.getName()));
		
		context.removeServiceListener(this);
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		String[] objectClass = (String[])
                event.getServiceReference().getProperty("objectClass");

        if (event.getType() == ServiceEvent.REGISTERED) {
            System.out.println("[OSGi] <BlackJackActivator::serviceChanged> Service of type " + objectClass[0] + " registered.");
        
        } else if (event.getType() == ServiceEvent.UNREGISTERING) {
            System.out.println("[OSGi] <BlackJackActivator::serviceChanged> Service of type " + objectClass[0] + " unregistered.");
        
        } else if (event.getType() == ServiceEvent.MODIFIED) {
            System.out.println("[OSGi] <BlackJackActivator::serviceChanged> Service of type " + objectClass[0] + " modified.");
        }
	}
}
