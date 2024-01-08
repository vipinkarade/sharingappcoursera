package com.example.sharingapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Collectors;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ContactList
{
	private ArrayList<Contact> contacts;
	private String FILENAME = "contacts.sav";

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
		for(Contact contact : contacts)
		{
			if(username.equals(contact.getUsername()))
			{
				return contact;
			}
		}
		return null;
	}

	public void loadContacts(Context context)
	{
		try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Contact>>() {}.getType();
            contacts = gson.fromJson(isr, listType); // temporary
            fis.close();
        } catch (FileNotFoundException e) {
            contacts = new ArrayList<Contact>();
        } catch (IOException e) {
            contacts = new ArrayList<Contact>();
        }
	}

	public void saveContacts(Context context)
	{
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, 0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			Gson gson = new Gson();
			gson.toJson(contacts, osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isUsernameAvailable(String username)
	{
		return getContactByUsername(username) == null ? true : false;
	}
}
