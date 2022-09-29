package com.example.intern.services.Impl;

import com.example.intern.model.User;
import com.example.intern.repository.UserRepository;
import com.example.intern.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);

	}
	public List<User> getAll(){
		return userRepository.findAll();
	}

	@Override
	public User findById(Long userId) throws Exception {
		return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId));
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(Long id, User user) throws Exception {
		User u = userRepository.findById(id).orElseThrow(() -> new Exception("User is not found"));
		u.setAddress(user.getAddress());
		u.setBirthday(user.getBirthday());
		u.setEmail(user.getEmail());
		u.setPhone(user.getPhone());
		u.setFullName(user.getFullName());
		u.setRoles(user.getRoles());
		u.setImageUser(user.getImageUser());
		u.setUsername(user.getUsername());
		u.setSex(user.getSex());
		return userRepository.save(u);
	}

	@Override
	public User findByFullName(String fullName) {
		return userRepository.findByFullName(fullName);
	}

	@Override
	public void remove(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User create(User u) {
		return userRepository.save(u);
	}
}
