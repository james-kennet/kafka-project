package com.demo.kafka.dao;

import com.demo.kafka.entity.AddressLife400;
import com.demo.kafka.entity.AddressSalesforce;
import com.demo.kafka.entity.AddressTransaction;
import com.demo.kafka.entity.AddressWebsite;
import com.demo.kafka.repository.AddressLife400Repository;
import com.demo.kafka.repository.AddressSalesforceRepository;
import com.demo.kafka.repository.AddressTransactionRepository;
import com.demo.kafka.repository.AddressWebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressDao {

	@Autowired
	private AddressSalesforceRepository addressSalesforceRepository;

	@Autowired
	private AddressWebsiteRepository addressWebsiteRepository;

	@Autowired
	private AddressLife400Repository addressLife400Repository;

	@Autowired
	private AddressTransactionRepository addressTransactionRepository;

	public void saveSalesforce(AddressSalesforce addressSalesforce) {
		addressSalesforceRepository.save(addressSalesforce);
	}

	public void saveWebsite(AddressWebsite addressWebsite) {
		addressWebsiteRepository.save(addressWebsite);
	}

	public void saveLife400(AddressLife400 addressLife400) {
		addressLife400Repository.save(addressLife400);
	}

	public void saveTransaction(AddressTransaction addressTransaction) {
		addressTransactionRepository.save(addressTransaction);
	}

	public void updateExistingAndInsertNewSalesForce(final String nric, AddressSalesforce addressSalesforce) {
		Optional<AddressSalesforce> optionalAddressSalesforce = addressSalesforceRepository.findByNric(nric);
		if(optionalAddressSalesforce.isPresent()) {
			optionalAddressSalesforce.get().setDeleted(true);
			addressSalesforceRepository.saveAndFlush(optionalAddressSalesforce.get());
		}
		addressSalesforceRepository.save(addressSalesforce);
	}

	public void updateExistingAndInsertNewWebsite(final String nric, AddressWebsite addressWebsite) {
		Optional<AddressWebsite> optionalAddressWebsite = addressWebsiteRepository.findByNric(nric);
		if(optionalAddressWebsite.isPresent()) {
			optionalAddressWebsite.get().setDeleted(true);
			addressWebsiteRepository.saveAndFlush(optionalAddressWebsite.get());
		}
		addressWebsiteRepository.save(addressWebsite);
	}


	public void updateExistingAndInsertNewLife400(final String nric, AddressLife400 addressLife400) {
		Optional<AddressLife400> optionalAddressLife400 = addressLife400Repository.findByNric(nric);
		if(optionalAddressLife400.isPresent()) {
			optionalAddressLife400.get().setDeleted(true);
			addressLife400Repository.saveAndFlush(optionalAddressLife400.get());
		}
		addressLife400Repository.save(addressLife400);
	}

	public void updateAddressTransaction(final Integer id, final String status) {
		Optional<AddressTransaction> optionalAddressTransaction = addressTransactionRepository.findById(id);
		if (optionalAddressTransaction.isPresent()) {
			optionalAddressTransaction.get().setStatus(status);
			addressTransactionRepository.saveAndFlush(optionalAddressTransaction.get());
		}
	}

	public List<AddressSalesforce> getAllSalesForce() {
		return addressSalesforceRepository.findAll();
	}

	public List<AddressWebsite> getAllWebsite() {
		return addressWebsiteRepository.findAll();
	}

	public List<AddressLife400> getAllLife400() {
		return addressLife400Repository.findAllByOrderByNric();
	}

	public List<AddressTransaction> getAllAddressTransactions() {
		return addressTransactionRepository.findAll();
	}

}
