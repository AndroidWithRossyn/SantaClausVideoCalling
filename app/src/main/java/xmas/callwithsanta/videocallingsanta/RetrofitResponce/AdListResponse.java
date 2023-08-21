package xmas.callwithsanta.videocallingsanta.RetrofitResponce;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AdListResponse{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("success")
	private int success;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}
}