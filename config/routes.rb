
Tagit::Application.routes.draw do


  resources :projects


  authenticated :user do
    root :to => 'home#index'
  end
  
  root :to => "home#welcome"
  devise_for :users
  resources :users

  resources :tags
  root to: "tags#index"

  
end
