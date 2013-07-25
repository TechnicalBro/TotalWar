package com.caved_in.CitizenTraits;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;

public class TraitRegister
{
	public TraitRegister()
	{
		CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(EventNPCTrait.class).withName("eventnpc"));
		CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(TravelNPCTrait.class).withName("travelnpc"));
		CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(ItemIdentifierNPCTrait.class).withName("appraiser"));
	}
}