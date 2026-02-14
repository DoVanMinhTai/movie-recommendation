import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { adminService } from "../../modules/admin/service/AdminService";
import toast from "react-hot-toast";

export default function MovieManage() {
  const queryClient = useQueryClient();

  const { data: movies, isLoading, isError } = useQuery({
    queryKey: ['admin-movies'],
    queryFn: adminService.getMovies,
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => adminService.deleteMovie(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['admin-movies'] });
      toast.success("Đã xóa phim thành công!");
    },
    onError: () => {
      toast.error("Có lỗi xảy ra khi xóa phim.");
    }
  });

  if (isLoading) return <div className="p-6 text-zinc-500">Đang tải danh sách phim...</div>;
  if (isError) return <div className="p-6 text-red-500">Không thể tải dữ liệu từ Server.</div>;

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-xl font-bold text-white">Danh sách phim</h2>
        <button className="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded text-sm font-bold transition">
          + THÊM PHIM
        </button>
      </div>

      <div className="bg-zinc-900 rounded-xl border border-zinc-800 overflow-hidden">
        <table className="w-full text-left border-collapse">
          <thead>
            <tr className="bg-zinc-800/50 text-zinc-400 text-xs uppercase">
              <th className="px-6 py-4 font-bold">ID / Poster</th>
              <th className="px-6 py-4 font-bold">Tiêu đề</th>
              <th className="px-6 py-4 font-bold text-right">Thao tác</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-zinc-800">
            {Array.isArray(movies) && movies.length > 0 ? (
              movies.map((item: any) => (
                <tr key={item.id} className="hover:bg-zinc-800/20 transition-colors">
                  <td className="px-6 py-4 flex items-center gap-3">
                    <img 
                      src={item.backdropPath ? `https://image.tmdb.org/t/p/w200${item.backdropPath}` : "https://via.placeholder.com/200x300?text=No+Image"} 
                      className="w-12 h-16 object-cover rounded shadow-md border border-zinc-700" 
                      alt={item.title}
                    />
                    <span className="text-zinc-500 text-xs font-mono">#{item.id}</span>
                  </td>
                  <td className="px-6 py-4 font-medium text-white">{item.title}</td>
                  <td className="px-6 py-4 text-right">
                    <div className="flex justify-end gap-2">
                      <button className="text-zinc-400 hover:text-white transition-colors text-sm">Sửa</button>
                      <button 
                        onClick={() => { if(window.confirm("Xác nhận xóa phim này?")) deleteMutation.mutate(item.id) }}
                        disabled={deleteMutation.isPending}
                        className="bg-red-500/10 text-red-500 p-2 rounded hover:bg-red-500 hover:text-white transition-all disabled:opacity-50"
                      >
                        {deleteMutation.isPending ? "..." : "Xóa"}
                      </button>
                    </div>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan={3} className="px-6 py-10 text-center text-zinc-500 italic">
                  Không tìm thấy phim nào trong danh sách.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}