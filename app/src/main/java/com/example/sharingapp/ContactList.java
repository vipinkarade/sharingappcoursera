package com.example.sharingapp;

import java.util.ArrayList;
import java.util.stream.Collectors;

import android.content.Context;

public class ContactList
{
	private ArrayList<Contact> contacts;
	private String FILENAME = "ContactList.java";

	public void setContacts(ArrayList<Contact> contacts)
	{
		this.contacts = contacts;
	}

	public ArrayList<Contact> getContacts()
	{
		return contacts;
	}

	public ArrayList<String> getAllUsernames()
	{
		return (ArrayList<String>) contacts.stream().map(contact -> contact.getUsername()).collect(Collectors.toList());
	}

	public void addContact(Contact contact)
	{
		contacts.add(contact);
	}

	public void deleteContact(Contact contact)
	{
		contacts.remove(contact);
	}

	public Contact getContact(int index)
	{
		return contacts.get(index);
	}

	public int getSize()
	{
		return contacts.size();
	}

	public int getIndex(Contact contact)
	{
		return contacts.indexOf(contact);
	}

	public boolean hasContact(Contact contact)
	{
		return contacts.contains(contact);
	}

	public Contact getContactByUsername(String username)
	{
		return contacts.stream().filter(contact -> contact.getUsername().equals(username)).findFirst().get();
	}

	public void loadContacts(Context context)
	{

	}

	public void saveContacts(Context context)
	{

	}

	public boolean isUsernameAvailable(String username)
	{
		return getContactByUsername(username) == null ? true : false;
	}
}
