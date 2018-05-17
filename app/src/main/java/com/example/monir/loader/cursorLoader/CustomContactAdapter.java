package com.example.monir.loader.cursorLoader;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.monir.loader.R;

public class CustomContactAdapter extends BaseAdapter {
	Cursor cursor;
	Context mContext;
	LayoutInflater inflater;

	public CustomContactAdapter(Context context, Cursor cursor) {
		mContext = context;
		this.cursor = cursor;
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return cursor.getCount();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		Holder holder;
		cursor.moveToPosition(position);
		if (view == null) {
			view = inflater.inflate(R.layout.contact_list_element, parent,
					false);
			holder = new Holder();
			holder.tvCOntactName = (TextView) view
					.findViewById(R.id.tvContactName);
			holder.tvContactNumber = (TextView) view
					.findViewById(R.id.tvContactNumber);
			holder.ivContactImage = (ImageView) view.findViewById(R.id.ivContactImage);
			view.setTag(holder);
		} else {
			holder = (Holder) view.getTag();
		}
		holder.tvCOntactName.setText(cursor.getString(cursor
				.getColumnIndex(Phone.DISPLAY_NAME)));
		holder.tvContactNumber.setText(cursor.getString(cursor
				.getColumnIndex(Phone.NUMBER)));
		String imageUri = cursor.getString(cursor
				.getColumnIndex(Phone.PHOTO_URI));
		try {
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(
					mContext.getContentResolver(), Uri.parse(imageUri));
			holder.ivContactImage.setImageBitmap(bitmap);
			scaleImage(holder.ivContactImage);
		} catch (Exception e) {
			holder.ivContactImage.setImageResource(R.drawable.contact);
			scaleImage(holder.ivContactImage);
		}
		return view;
	}

	class Holder {
		TextView tvCOntactName, tvContactNumber;
		ImageView ivContactImage;
	}

	private void scaleImage(ImageView imageView) {

		Drawable drawing = imageView.getDrawable();
		if (drawing == null) {
		}
		Bitmap bitmap = ((BitmapDrawable) drawing).getBitmap();

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int bounding = dpToPx(50);

		float xScale = ((float) bounding) / width;
		float yScale = ((float) bounding) / height;
		float scale = (xScale <= yScale) ? xScale : yScale;
		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);

		Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		width = scaledBitmap.getWidth(); // re-use
		height = scaledBitmap.getHeight(); // re-use
		BitmapDrawable result = new BitmapDrawable(scaledBitmap);

		imageView.setImageDrawable(result);

		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView
				.getLayoutParams();
		params.width = width;
		params.height = height;
		imageView.setLayoutParams(params);

	}

	private int dpToPx(int dp) {
		float density = mContext.getResources().getDisplayMetrics().density;
		return Math.round((float) dp * density);
	}
}
