package vn.ntu.edu.TrungKhiem.frament;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import vn.ntu.edu.TrungKhiem.controller.ICartController;
import vn.ntu.edu.TrungKhiem.model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class first_fragment extends Fragment {

    RecyclerView rvListProduct;
    ProductAdapter adapter;
    List<Product> listProducts;
    FloatingActionButton btnCart;

    public first_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.first_fragment, container, false);
        rvListProduct = (RecyclerView) rootView.findViewById(R.id.rvListProduct);
        rvListProduct.setLayoutManager(new LinearLayoutManager(getActivity()));

        ICartController controller = (ICartController) this.getActivity().getApplication();
        listProducts = controller.listAllProducts();
        adapter = new ProductAdapter(listProducts);
        rvListProduct.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.my_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mnuCart:
                goToSecondFragment();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToSecondFragment() {
        NavController navController = NavHostFragment.findNavController(first_fragment.this);
        navController.navigate(R.id.action_first_fragment_to_second_fragmet);
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice, txtDesc;
        ImageView imvAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtName);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDesc = this.itemView.findViewById(R.id.txtDesc);
            imvAddToCart = this.itemView.findViewById(R.id.imvAddToCart);
        }

        public void bind(final Product p) {
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());
            imvAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ICartController controller = (ICartController) getActivity().getApplication();
                    String result = "Mặt hàng đã có trong giỏ";
                    if(controller.addToCart(p)) {
                        result = "Thêm mặt hàng thành công";
                    }
                    Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
        List<Product> listProducts;

        public ProductAdapter(List<Product> listProducts) {
            this.listProducts = listProducts;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(listProducts.get(position));
        }

        @Override
        public int getItemCount() {
            return listProducts.size();
        }
    }
}
