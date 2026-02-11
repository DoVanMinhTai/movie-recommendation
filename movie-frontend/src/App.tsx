import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import HomePage from './pages/homepage/HomePage'
import MainLayout from './common/layouts/MainLayout'
import AdminLayout from './common/layouts/AdminLayout'
import Login from './pages/login/Login'
import Register from './pages/register/Register'
import MovieDetail from './pages/movie/Movie'
import SearchResult from './pages/search/SearchResult'
import Profile from './pages/profile/Profile'
import WatchList from './pages/watchlist/WatchList'
import Dashboard from './pages/admin/Dashboard'
import MovieManage from './pages/admin/MovieManage'
import Category from './pages/category/Category'
import "./index.css";
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      retry: 1,
      staleTime: 5 * 60 * 1000,
    },
  },
});

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          {/* <Route path="/onboarding" element={<OnBoarding />} /> */}

          <Route path='/' element={<MainLayout />}>
            <Route index element={<HomePage />} />
            <Route path="movie/:id" element={<MovieDetail />} />
            <Route path="search" element={<SearchResult />} />
            <Route path="category/:type" element={<Category />} />
            <Route path="profile" element={<Profile />} />
            <Route path="watchlist" element={<WatchList />} />
          </Route>

          <Route path="/admin" element={<AdminLayout />}>
            <Route index element={<Dashboard />} />
            <Route path="movies" element={<MovieManage />} />
            <Route path="users" element={<div>Quản lý người dùng</div>} />
          </Route>

          <Route path="*" element={<h1>404 - Not Found</h1>} />

        </Routes>
      </BrowserRouter>
    </QueryClientProvider>
  )
}
export default App