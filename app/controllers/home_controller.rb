class HomeController < ApplicationController 
  def index
    @users = User.all
  end
  
  def welcome
  end
  
  def devise_mapping
      @devise_mapping ||= Devise.mappings[:user]
    end
end
