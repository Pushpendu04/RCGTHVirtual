package com.cognizant.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.Profile;
import com.cognizant.exception.ProfileAlreadyExistException;
import com.cognizant.exception.ProfileNotFoundException;
import com.cognizant.service.ProfileService;

@Repository
public class ProfileDaoCache {

	public static final String HASH_KEY = "Profile";

	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate template;

	@Autowired
	ProfileService service;

	@SuppressWarnings("unchecked")
	public Profile insertProfiles(Profile profile) throws ProfileAlreadyExistException {
		if (template.opsForHash().get(HASH_KEY, profile.getProfiletype()) != null) {
			throw new ProfileAlreadyExistException("Already Exist!");
		} else {
			Profile insertProfiles = service.insertProfiles(profile);
			template.opsForHash().put(HASH_KEY, insertProfiles.getProfiletype(), insertProfiles);
			return insertProfiles;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Profile> getAllProfiles() {
		List<Profile> profiles = template.opsForHash().values(HASH_KEY);
		if (profiles.isEmpty()) {
			return (List<Profile>) service.getAllProfiles();
		} else {
			return profiles;
		}
	}

	@SuppressWarnings("unchecked")
	public String deleteProfiles(String type) throws ProfileNotFoundException {
		String res=service.deleteProfiles(type);
		if(res.equals(type+" Profile Deleted")){
		template.opsForHash().delete(HASH_KEY, type);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public Profile updateProfiles(Profile profile) throws ProfileNotFoundException {
		if (template.opsForHash().get(HASH_KEY, profile.getProfiletype()) == null) {
			throw new ProfileNotFoundException("Not Found!");
		} else {
			Profile updatedProfile=service.updateProfiles(profile);
			template.opsForHash().put(HASH_KEY, updatedProfile.getProfiletype(), updatedProfile);
			return updatedProfile;
		}
	}

}
