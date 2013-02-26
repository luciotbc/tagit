class Project < ActiveRecord::Base
  belongs_to :user
  attr_accessible :goal, :name
  
  validate :user, :presence => true
end
