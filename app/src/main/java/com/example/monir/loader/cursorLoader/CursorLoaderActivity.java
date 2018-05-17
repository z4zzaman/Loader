package com.example.monir.loader.cursorLoader;

import android.Manifest;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.ListView;

import com.example.monir.loader.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.single.PermissionListener;

public class CursorLoaderActivity extends FragmentActivity implements
		LoaderCallbacks<Cursor> {
	ListView lstContact;
	CustomContactAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cursor_loader);
		lstContact = findViewById(R.id.lstContacts);
		getSupportLoaderManager().initLoader(1, null, this);		
	}



	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		Uri CONTACT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		CursorLoader cursorLoader = new CursorLoader(this, CONTACT_URI, null,
				null, null, null);
		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
		cursor.moveToFirst();
		adapter = new CustomContactAdapter(this, cursor);
		if (lstContact !=null)
		lstContact.setAdapter(adapter);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {

	}
}
