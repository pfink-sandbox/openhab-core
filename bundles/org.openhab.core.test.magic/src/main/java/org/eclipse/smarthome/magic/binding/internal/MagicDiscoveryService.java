/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.smarthome.magic.binding.internal;

import static org.eclipse.smarthome.magic.binding.MagicBindingConstants.THING_TYPE_CONFIG_THING;

import java.util.Collections;
import java.util.UUID;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.config.discovery.AbstractDiscoveryService;
import org.eclipse.smarthome.config.discovery.DiscoveryResult;
import org.eclipse.smarthome.config.discovery.DiscoveryResultBuilder;
import org.eclipse.smarthome.config.discovery.DiscoveryService;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.osgi.service.component.annotations.Component;

/**
 * The {@link MagicDiscoveryService} magically discovers magic things.
 *
 * @author Henning Treu - Initial contribution
 */
@NonNullByDefault
@Component(service = DiscoveryService.class, immediate = true)
public class MagicDiscoveryService extends AbstractDiscoveryService {

    public MagicDiscoveryService() throws IllegalArgumentException {
        super(Collections.singleton(THING_TYPE_CONFIG_THING), 0);
    }

    @Override
    protected void startScan() {
        String serialNumber = createRandomSerialNumber();
        DiscoveryResult discoveryResult = DiscoveryResultBuilder
                .create(new ThingUID(THING_TYPE_CONFIG_THING, serialNumber))
                .withRepresentationProperty(Thing.PROPERTY_SERIAL_NUMBER)
                .withProperty(Thing.PROPERTY_SERIAL_NUMBER, serialNumber).withLabel("Magic Thing").build();
        thingDiscovered(discoveryResult);
    }

    private String createRandomSerialNumber() {
        return UUID.randomUUID().toString();
    }

}
