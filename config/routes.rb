Tagit::Application.routes.draw do

  authenticated :user do
    root :to => 'home#index'
  end
  
  root :to => "home#welcome"
  devise_for :users
  resources :users
end
