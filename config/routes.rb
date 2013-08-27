
Tagit::Application.routes.draw do


root :to => "projects#index"
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
