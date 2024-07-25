package com.demo.kafka.service.impl;

import com.demo.kafka.constant.Constants;
import com.demo.kafka.dao.AddressDao;
import com.demo.kafka.dto.Address;
import com.demo.kafka.entity.AddressLife400;
import com.demo.kafka.entity.AddressSalesforce;
import com.demo.kafka.entity.AddressTransaction;
import com.demo.kafka.entity.AddressWebsite;
import com.demo.kafka.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;

	@Override
	public void save(Address address) {
		switch (address.getClientType()) {
			case Constants.CLIENT_TYPE_SALESFORCE:
				AddressSalesforce addressSalesforce = new AddressSalesforce();
				BeanUtils.copyProperties(
					address,
					addressSalesforce,
					"id",
													"clientType"
				);
				addressDao.saveSalesforce(addressSalesforce);
				break;
			case Constants.CLIENT_TYPE_WEBSITE:
				AddressWebsite addressWebsite = new AddressWebsite();
				BeanUtils.copyProperties(
					address,
					addressWebsite,
					"id",
													"clientType"
				);
				addressDao.saveWebsite(addressWebsite);
				break;
			case Constants.CLIENT_TYPE_LIFE400:
				AddressLife400 addressLife400 = new AddressLife400();
				BeanUtils.copyProperties(
					address,
					addressLife400,
					"id",
													"clientType"
				);
				addressDao.saveLife400(addressLife400);
				break;
		}
		AddressTransaction addressTransaction = new AddressTransaction();
		BeanUtils.copyProperties(
			address,
			addressTransaction,
			"id"
		);
		addressTransaction.setStatus("CREATED");
		addressDao.saveTransaction(addressTransaction);
	}

	public void saveAddressTransaction(Address address) {
		AddressTransaction addressTransaction = new AddressTransaction();
		BeanUtils.copyProperties(
			address,
			addressTransaction
		);
		if(!Constants.ADDRESS_TRANSACTION_PENDING.equals(address.getStatus())) {
			addressTransaction.setStatus(Constants.ADDRESS_TRANSACTION_PENDING);
		} else {
			addressTransaction.setStatus(Constants.ADDRESS_TRANSACTION_REJECTED);
		}
		addressDao.saveTransaction(addressTransaction);
	}

	@Override
	@Transactional
	public void updateToAll(Address address) {
		updateWebsite(address);
		updateSalesforce(address);
		updateLife400(address);
		updateTransaction(address.getId(), "UPDATED");
	}


	private void updateWebsite(final Address address) {
		AddressWebsite addressWebsite = new AddressWebsite();
		BeanUtils.copyProperties(
			address,
			addressWebsite,
			"clientType", "id"
		);
		addressDao.updateExistingAndInsertNewWebsite(address.getNric(), addressWebsite);
	}

	private void updateSalesforce(final Address address) {
		AddressSalesforce addressSalesforce = new AddressSalesforce();
		BeanUtils.copyProperties(
			address,
			addressSalesforce,
			"clientType", "id"
		);
		addressDao.updateExistingAndInsertNewSalesForce(address.getNric(), addressSalesforce);
	}

	private void updateLife400(final Address address) {
		AddressLife400 addressLife400 = new AddressLife400();
		BeanUtils.copyProperties(
			address,
			addressLife400,
			"clientType", "id"
		);
		addressDao.updateExistingAndInsertNewLife400(address.getNric(), addressLife400);
	}

	private void updateTransaction(final Integer id, final String status) {
		addressDao.updateAddressTransaction(id, status);
	}


	@Override
	public List<Address> getByClientType(final String clientType) {
		switch (clientType) {
			case Constants.CLIENT_TYPE_SALESFORCE:
				List<AddressSalesforce> addressSalesforces = addressDao.getAllSalesForce();
				return addressSalesforces
					.stream()
					.map(this::getAddress).map(c -> setClientType(c, Constants.CLIENT_TYPE_SALESFORCE)).collect(Collectors.toList());

			case Constants.CLIENT_TYPE_WEBSITE:
				List<AddressWebsite> addressWebsites = addressDao.getAllWebsite();
				return addressWebsites
					.stream()
					.map(this::getAddress).map(c -> setClientType(c, Constants.CLIENT_TYPE_WEBSITE)).collect(Collectors.toList());

			case Constants.CLIENT_TYPE_LIFE400:
				List<AddressLife400> addressLife400s = addressDao.getAllLife400();
				List<Address> list= addressLife400s
					.stream()
					.map(this::getAddress).map(c -> setClientType(c, Constants.CLIENT_TYPE_LIFE400)).collect(Collectors.toList());
				return list;
			case Constants.ADDRESS_TRANSACTION:
				List<AddressTransaction> addressTransactions = addressDao.getAllAddressTransactions();
				return addressTransactions
					.stream()
					.map(this::getAddressFromAddressTransactions).collect(Collectors.toList());

		}

		return Collections.emptyList();

	}


	private Address getAddress(final Object clientAddress) {
		Address address = Address.builder().build();
		BeanUtils.copyProperties(
			clientAddress,
			address,
			"clientType"
		);
		return address;
	}
	private Address setClientType(Address address, String clientType) {
		address.setClientType(clientType);
		return address;
	}

	private Address getAddressFromAddressTransactions(AddressTransaction addressTransaction) {
		Address address = Address.builder().build();
		BeanUtils.copyProperties(
			addressTransaction,
			address
		);
		return address;
	}

}
